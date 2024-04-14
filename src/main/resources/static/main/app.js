// Function to load products and display them
async function loadProducts() {
  try {
    const productsContainer = document.querySelector(".products");

    const response = await fetch("http://localhost:8080/coffees");
    const products = await response.json();

    console.log("Output: ", products);

    products.forEach((product) => {
      const productItem = document.createElement("div");
      productItem.classList.add("product");
      productItem.innerHTML = `
        <img src="${product.image}" alt="${product.title}">
        <div class="product-title">${product.title}</div>
        <div class="product-price">$${product.price}</div>
        <div class="product-quantity">In Storage: ${product.quantityInStorage}</div>
        <button onclick="addToCart('${product._id}', '${product.title}', ${product.price})">Add to Cart</button>
      `;
      productsContainer.appendChild(productItem);
    });
  } catch (error) {
    console.error("Error fetching products:", error);
  }
}

// Function to toggle the cart panel
function toggleCart() {
  const cartPanel = document.querySelector(".cart-panel");
  cartPanel.classList.toggle("active");

  // Calculate total price when opening the cart
  calculateTotal();
}

// Function to add product to cart
function addToCart(_id, title, price) {
  // Check if the product is already in the cart
  const cartItems = document.querySelectorAll(".cart-item");
  let found = false;

  cartItems.forEach((item) => {
    if (item.dataset.productId === String(_id)) {
      found = true;
      let quantity = parseInt(item.dataset.quantity) + 1;
      item.innerHTML = `${quantity}x ${title} - $${
        price * quantity
      } <button class="decrease-btn" onclick="decreaseQuantity('${_id}')">-</button> <button class="increase-btn" onclick="increaseQuantity('${_id}')">+</button>`;
      item.dataset.quantity = quantity;
    }
  });

  if (!found) {
    // Create an object representing the product
    const product = { _id: _id, title: title, price: price, quantity: 1 };

    // Add the item to the cart panel
    const cartContent = document.querySelector(".cart-content");
    const cartItem = document.createElement("div");
    cartItem.classList.add("cart-item");
    cartItem.dataset.productId = String(_id);
    cartItem.dataset.quantity = "1";
    cartItem.dataset.title = title; // Retain the product title in the dataset
    cartItem.dataset.price = price; // Retain the product price in the dataset
    cartItem.innerHTML = `1x ${title} - $${price} <button class="decrease-btn" onclick="decreaseQuantity('${_id}')">-</button> <button class="increase-btn" onclick="increaseQuantity('${_id}')">+</button>`;
    cartContent.appendChild(cartItem);
  }

  // Calculate total price whenever an item is added
  calculateTotal();
}

// Function to decrease the quantity of an item in the cart
function decreaseQuantity(_id) {
  const cartItems = document.querySelectorAll(".cart-item");

  cartItems.forEach((item) => {
    if (item.dataset.productId === String(_id)) {
      let quantity = parseInt(item.dataset.quantity);
      if (quantity > 1) {
        quantity--;
        item.innerHTML = `${quantity}x ${item.dataset.title} - $${
          item.dataset.price * quantity
        } <button class="decrease-btn" onclick="decreaseQuantity('${_id}')">-</button> <button class="increase-btn" onclick="increaseQuantity('${_id}')">+</button>`;
        item.dataset.quantity = quantity;
      } else {
        // Remove the item from the cart if the quantity becomes 0
        item.remove();
      }
    }
  });

  // Recalculate total price when quantity is decreased
  calculateTotal();
}

// Function to increase the quantity of an item in the cart
function increaseQuantity(_id) {
  const cartItems = document.querySelectorAll(".cart-item");

  cartItems.forEach((item) => {
    if (item.dataset.productId === String(_id)) {
      let quantity = parseInt(item.dataset.quantity);
      quantity++;
      item.innerHTML = `${quantity}x ${item.dataset.title} - $${
        item.dataset.price * quantity
      } <button class="decrease-btn" onclick="decreaseQuantity('${_id}')">-</button> <button class="increase-btn" onclick="increaseQuantity('${_id}')">+</button>`;
      item.dataset.quantity = quantity;
    }
  });

  // Recalculate total price when quantity is increased
  calculateTotal();
}

// Function to calculate the total price of all items in the cart
function calculateTotal() {
  const cartItems = document.querySelectorAll(".cart-item");
  let totalPrice = 0;

  cartItems.forEach((item) => {
    const itemPrice = parseFloat(item.innerHTML.split("$")[1]); // Extract item price
    totalPrice += itemPrice;
  });

  const cartPanel = document.querySelector(".cart-panel");
  const totalElement = document.createElement("div");
  totalElement.classList.add("total");
  totalElement.innerHTML = `<strong>Total Price:</strong> $${totalPrice.toFixed(
    2
  )}`;

  // Remove previous total price element before appending a new one
  const existingTotal = document.querySelector(".total");
  if (existingTotal) {
    cartPanel.removeChild(existingTotal);
  }

  cartPanel.appendChild(totalElement);
}

// Function to clear the cart (remove all items)
function clearCart() {
  const cartContent = document.querySelector(".cart-content");
  cartContent.innerHTML = ""; // Clear all cart items

  // Clear total price when cart is cleared
  const cartPanel = document.querySelector(".cart-panel");
  const existingTotal = document.querySelector(".total");
  if (existingTotal) {
    cartPanel.removeChild(existingTotal);
  }
}

// Function to authenticate user credentials
function authenticate(username, password) {
  return fetch("http://localhost:8080/credentials")
    .then((response) => response.json())
    .then((credentials) => {
      const foundUser = credentials.find(
        (cred) => cred.username === username && cred.password === password
      );
      return foundUser ? true : false;
    })
    .catch((error) => {
      console.error("Error fetching credentials:", error);
      return false;
    });
}

// Function to handle login form submission
function login(event) {
  event.preventDefault();

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  authenticate(username, password).then((isAuthenticated) => {
    if (isAuthenticated) {
      // Store authentication status in localStorage
      localStorage.setItem("loggedIn", "true");
      localStorage.setItem("username", username);
      window.location.href = "index.html"; // Redirect to main page on successful login
    } else {
      alert("Invalid credentials. Please try again.");
    }
  });
}
// Function to log out (clears authentication status and redirects to login page)
function logout() {
  localStorage.removeItem("loggedIn");
  window.location.href = "login.html";
}

// Function to check if user is logged in
function checkLogin() {
  const isLoggedIn = localStorage.getItem("loggedIn");
  if (!isLoggedIn || isLoggedIn !== "true") {
    window.location.href = "login.html"; // Redirect to login page if not logged in
  }
}
// Function to generate a random unique string to serve as purchase code
function generateRandomString(length) {
  const characters =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  let result = "";
  const charactersLength = characters.length;

  for (let i = 0; i < length; i++) {
    result += characters.charAt(Math.floor(Math.random() * charactersLength));
  }

  return result;
}
// Function to confirm purchase
async function confirmPurchase() {
  const isLoggedIn = localStorage.getItem("loggedIn");
  const storedUsername = localStorage.getItem("username");
  if (!isLoggedIn || isLoggedIn !== "true" || !storedUsername) {
    alert("Please login to confirm your purchase.");
    return;
  }

  // Calculate the total price
  calculateTotal();

  const cartItems = document.querySelectorAll(".cart-item");
  const purchaseDetails = [];
  const cartProducts = [];
  cartItems.forEach((item) => {
    const productId = item.dataset.productId;
    const quantity = parseInt(item.dataset.quantity);
    const title = item.innerHTML.split("x")[1].trim().split("-")[0].trim();
    const price = parseFloat(item.innerHTML.split("$")[1]);

    purchaseDetails.push({ productId, title, quantity, price });
    cartProducts.push({ productId, quantity });
  });

  try {
    const response = await fetch("http://localhost:8080/coffees");
    const products = await response.json();

    for (const cartProduct of cartProducts) {
      const product = products.find(
        (prod) => prod._id === cartProduct.productId
      );

      if (!product) {
        alert("Error: Product not found.");
        return;
      }

      if (cartProduct.quantity > product.quantityInStorage) {
        alert(
          `The quantity of ${product.title} in your cart exceeds the available quantity in storage. Please update your cart.`
        );
        return;
      }
    }

    const purchaseCode = generateRandomString(10);
    const totalPrice = parseFloat(
      document.querySelector(".total").textContent.split("$")[1]
    );
    const status = "Shipping soon";
    var currentDate = new Date();

    // Get the current year, month, day, and hour
    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth() + 1; // Months are zero-based, so add 1
    const currentDay = currentDate.getDate();
    const currentHour = currentDate.getHours();
    const currentMinute = currentDate.getMinutes();

    // Format the date and hour as needed
    const formattedHour = currentHour + ":" + currentMinute; // Assuming you want the hour in HH:00 format
    const purchaseDate =
      currentYear + "-" + currentMonth + "-" + currentDay + "-" + formattedHour;

    const purchaseData = {
      username: storedUsername,
      purchaseCode: purchaseCode,
      items: purchaseDetails,
      totalPrice: totalPrice,
      status: status,
      purchaseDate: purchaseDate,
    };

    sendPurchaseDataToServer(purchaseData);
  } catch (error) {
    console.error("Error confirming purchase:", error);
    alert("There was an error confirming your purchase. Please try again.");
  }
}

// Function to send purchase data to server using RESTful API
async function sendPurchaseDataToServer(data) {
  try {
    const response = await fetch("http://localhost:8080/purchases", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    const result = await response.json();

    console.log("Purchase confirmed:", result);
    const purchaseCode = data.purchaseCode;
    alert(
      `Purchase confirmed! Your purchase code is: ${purchaseCode}\nThank you for shopping with us.`
    );

    // After successful purchase, update the quantity in storage
    await updateQuantityInStorage(data.items);

    clearCart(); // Clear the cart after purchase confirmation
    // Store purchase information in local storage
    localStorage.setItem("lastPurchase", JSON.stringify(data));
    // Reset the page after closing the alert
    window.location.href = "orderStatus.html";
  } catch (error) {
    console.error("Error confirming purchase:", error);
    alert("There was an error confirming your purchase. Please try again.");
  }
}
async function updateQuantityInStorage(purchasedItems) {
  for (const item of purchasedItems) {
    try {
      const product = await fetchProductData(item.productId);
      const currentQuantityInStorage = parseInt(product.quantityInStorage);
      const purchasedQuantity = parseInt(item.quantity);

      if (!isNaN(currentQuantityInStorage) && !isNaN(purchasedQuantity)) {
        const updatedQuantity = currentQuantityInStorage - purchasedQuantity;

        // Update the quantity in storage for the purchased product
        await updateProductQuantity(item.productId, updatedQuantity);
      } else {
        console.error(
          `Error: Invalid quantity values for product ID ${item.productId}`
        );
        // Handle error scenario if needed
      }
    } catch (error) {
      console.error(
        `Error updating quantity for product ID ${item.productId}:`,
        error
      );
      // Handle error scenario if needed
    }
  }
}
async function fetchProductData(productId) {
  try {
    const response = await fetch(`http://localhost:8080/coffees/${productId}`);
    if (!response.ok) {
      throw new Error("Failed to fetch product data");
    }
    const product = await response.json();
    return product;
  } catch (error) {
    console.error("Error fetching product data:", error);
    throw error; // Propagate the error to the caller if needed
  }
}

async function updateProductQuantity(productId, updatedQuantity) {
  try {
    const updateResponse = await fetch(
      `http://localhost:8080/coffees/${productId}`,
      {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          quantityInStorage: updatedQuantity,
        }),
      }
    );

    if (!updateResponse.ok) {
      throw new Error("Failed to update product quantity");
    }
    console.log(`Quantity updated for product ID ${productId}`);
  } catch (error) {
    console.error(
      `Error updating quantity for product ID ${productId}:`,
      error
    );
    throw error; // Propagate the error to the caller if needed
  }
}

function switchToAdmin() {
  window.location.href = "../admin/AdminPageLogin.html";
}
// Check login status when loading the main page
if (
  window.location.pathname === "/index.html" ||
  window.location.pathname === "/"
) {
  checkLogin();
}

// Call the function to load products when the page loads
loadProducts();
