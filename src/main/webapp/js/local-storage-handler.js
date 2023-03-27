import {updateCart} from "./cart-widget.js";

export class ShoppingCart {
    constructor(userID) {
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
        this.saveToLocalStorage();
    }


    removeItem(item) {
        const productId = item.productId; // assuming each item has a unique 'id' property
        const index = this.items.findIndex(i => i.productId === productId);
        // const index = this.items.indexOf(item);
        console.log(this.items)
        console.log(item)
        if (index !== -1) {
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        }
    }

    updateItemQuantity(item, quantity) {
        item.quantity = quantity;
        this.saveToLocalStorage();
    }

    clear() {
        this.items = [];
        this.saveToLocalStorage();
    }

    saveToLocalStorage() {
        if (!this.userID)
            var userId = getUserId(); // get user ID from session or cookie
        else
            var userId = this.userID
        if (userId) {
            const key = `cart-${userId}`;
            const value = JSON.stringify(this.items);
            localStorage.setItem(key, value);
        }
        updateCart()
    }

    loadFromLocalStorage() {
        if (!this.userID)
            var userId = getUserId(); // get user ID from session or cookie
        else
            var userId = this.userID

        // const userId = getUserId(); // get user ID from session or cookie
        if (userId) {
            const key = `cart-${userId}`;
            const value = localStorage.getItem(key);
            if (value) {
                console.log("exist")
                this.items = JSON.parse(value);
            }
        }
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