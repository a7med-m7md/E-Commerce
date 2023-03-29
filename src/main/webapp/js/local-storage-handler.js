import {updateCart} from "./cart-widget.js";

export class ShoppingCart {
    constructor(userID, load) {
        console.log("loggedIn")
        this.userID = userID;
        this.items = [];

        this.loadFromLocalStorage();
    }

    addItem(item) {
        let existingItem = this.items.find(
            (cartItem) => cartItem.productId === item.productId
        );
        if (existingItem) {
            existingItem.quantity += item.quantity;
        } else {
            this.items.push(item);
        }
        this.saveToLocalStorage(true);
    }

    removeItem(item) {
        const productId = item.productId; // assuming each item has a unique 'id' property
        const index = this.items.findIndex(i => i.productId === productId);
        if (index !== -1) {
            this.items.splice(index, 1);
            this.saveToLocalStorage(true);
        }
    }

    removeAll() {
        this.items = [];
        this.saveToLocalStorage(true);
    }

    updateItemQuantity(item, quantity) {
        item.quantity = quantity;
        this.saveToLocalStorage(true);
    }

    clear() {
        this.items = [];
        this.saveToLocalStorage(true);
    }

    saveToLocalStorage(updateCartEnabled = true) {
        if (!this.userID)
            var userId = "00000000-0000-0000-0000-000000000000"; // get user ID from session or cookie
        else
            var userId = this.userID
        if (userId) {
            const key = `cart-${userId}`;
            const value = JSON.stringify(this.items);
            localStorage.setItem(key, value);
        }
        if (updateCartEnabled) {
            updateCart();
        }
    }

    loadFromLocalStorage() {
        if (!this.userID)
            var userId = "00000000-0000-0000-0000-000000000000"; // get user ID from session or cookie
        else
            var userId = this.userID
        if (userId) {
            const key = `cart-${userId}`;
            const value = localStorage.getItem(key);
            if (value) {
                console.log("exist")
                this.items = JSON.parse(value);
            }
        }
    }

    mergeWithGivenCart(givenUserID = "00000000-0000-0000-0000-000000000000") {
        console.log("--2-3-")
        const givenCart = new ShoppingCart(givenUserID);
        // givenCart.loadFromLocalStorage();

        givenCart.items.forEach((item) => {
            this.addItem(item);
        });

        this.saveToLocalStorage(false);
        // window.location.reload()
        givenCart.clear()
    }

    mergeWithCurrentCart() {
        const currentCart = new ShoppingCart(this.userID);
        currentCart.loadFromLocalStorage();
        currentCart.mergeItemsByKey(`cart-${this.userID}`, "cart-00000000-0000-0000-0000-000000000000");
        console.log("-------")
        console.log(currentCart.items)
        this.items = currentCart.items;
        this.saveToLocalStorage();

        const oldCart = new ShoppingCart("00000000-0000-0000-0000-000000000000");
        oldCart.clear();
        oldCart.saveToLocalStorage(false);
    }

    mergeItemsByKey(keyToMerge, targetKey) {
        this.items.forEach((item) => {
            if (item.hasOwnProperty(keyToMerge)) {
                const targetItem = this.items.find(
                    (i) => i.hasOwnProperty(targetKey) && i[targetKey] === item[keyToMerge]
                );
                if (targetItem) {
                    targetItem.quantity += item.quantity;
                    this.removeItem(item);
                } else {
                    const newItem = {...item};
                    newItem[targetKey] = item[keyToMerge];
                    delete newItem[keyToMerge];
                    this.addItem(newItem);
                    this.removeItem(item);
                }
            }
        });
    }
}