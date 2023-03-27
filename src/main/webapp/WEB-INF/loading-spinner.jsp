<%--
  Created by IntelliJ IDEA.
  User: Ahmed Mohamed
  Date: 3/27/2023
  Time: 7:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <script>
    window.onload = function(){
      var overlay = document.getElementById("overlay");
      overlay.style.display = 'none';
    };
  </script>
  <style>
    #overlay {
      display: flex;
      align-items: center;
      justify-content: center;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.5);
      z-index: 9999;
    }

    .spinner {
      border: 4px solid #f3f3f3;
      border-top: 4px solid #80061b;
      border-radius: 50%;
      width: 40px;
      height: 40px;
      animation: spin 1s linear infinite;
    }

    @keyframes spin {
      0% { transform: rotate(0deg); }
      100% { transform: rotate(360deg); }
    }
  </style>
</head>
<body>
<div id="overlay">
  <div class="spinner"></div>
</div>
</body>
</html>
