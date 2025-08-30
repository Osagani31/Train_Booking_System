<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Card Payment Gateway</title>
<link rel="stylesheet" href="Css/UserHome.css">
<style>
body {
    background: #fff0f5;
}
.payment-container {
    display: flex;
    justify-content: center;
    align-items: flex-start;
    gap: 48px;
    margin-top: 48px;
    flex-wrap: wrap;
}
.payment-form {
    background: #fff;
    border-radius: 16px;
    box-shadow: 0 4px 24px rgba(255,183,197,0.10);
    padding: 40px 36px;
    max-width: 400px;
    width: 100%;
    border: 1px solid #ffd6e0;
    font-family: 'Poppins', sans-serif;
}
.payment-form h2 {
    color: #e75480;
    margin-bottom: 24px;
    font-size: 2rem;
    font-weight: 700;
    letter-spacing: 1px;
}
.payment-form label {
    display: block;
    margin-bottom: 8px;
    color: #e75480;
    font-weight: 500;
}
.payment-form input[type="text"],
.payment-form input[type="tel"],
.payment-form input[type="password"] {
    width: 100%;
    padding: 12px 15px;
    margin-bottom: 18px;
    border: 1px solid #ffb7c5;
    border-radius: 8px;
    font-size: 1rem;
    background: #fff6fa;
    transition: border 0.2s, box-shadow 0.2s;
}
.payment-form input:focus {
    border-color: #e75480;
    box-shadow: 0 0 0 2px rgba(231,84,128,0.13);
    outline: none;
}
.payment-form input[type="submit"] {
    background: linear-gradient(90deg, #ffb7c5 0%, #e75480 100%);
    color: #fff;
    border: none;
    border-radius: 8px;
    padding: 14px 0;
    font-size: 1.1rem;
    font-weight: 600;
    cursor: pointer;
    width: 100%;
    margin-top: 10px;
    transition: background 0.2s, box-shadow 0.2s, transform 0.1s;
    box-shadow: 0 2px 8px rgba(255,183,197,0.13);
}
.payment-form input[type="submit"]:hover {
    background: linear-gradient(90deg, #e75480 0%, #ffb7c5 100%);
    transform: translateY(-2px);
}
.card-images {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    gap: 18px;
    margin-top: 10px;
    background: #fff6fa;
    border-radius: 16px;
    padding: 24px 18px;
    box-shadow: 0 2px 8px rgba(255,183,197,0.10);
}
.card-images img {
    width: 90px;
    height: 60px;
    object-fit: contain;
    margin-bottom: 8px;
    border-radius: 8px;
    background: #fff;
    box-shadow: 0 2px 8px rgba(255,183,197,0.07);
    border: 1px solid #ffd6e0;
}
/* Error popup */
.error-popup {
    position: fixed;
    bottom: 32px;
    right: 32px;
    background: #e75480;
    color: #fff;
    padding: 18px 32px;
    border-radius: 10px;
    font-size: 1.1rem;
    box-shadow: 0 4px 24px rgba(231,84,128,0.13);
    z-index: 9999;
    display: flex;
    align-items: center;
    gap: 16px;
    animation: fadeIn 0.5s;
}
@keyframes fadeIn {
    from { opacity: 0; transform: translateY(30px); }
    to { opacity: 1; transform: translateY(0); }
}
.close-btn {
    background: none;
    border: none;
    color: #fff;
    font-size: 1.3rem;
    cursor: pointer;
    margin-left: 10px;
}
@media (max-width: 900px) {
    .payment-container {
        flex-direction: column;
        align-items: center;
    }
    .card-images {
        flex-direction: row;
        justify-content: center;
        align-items: center;
        margin-top: 24px;
    }
}
</style>
<script>
function closeErrorPopup() {
    var popup = document.getElementById('errorPopup');
    if (popup) popup.style.display = 'none';
}
</script>
</head>
<body>
<div class="payment-container">
    <form class="payment-form" action="payment" method="post">
        <h2>Card Payment Gateway</h2>
        <label for="cardNumber">CARD NUMBER</label>
        <input type="tel" name="cardNumber" placeholder="Valid Card Number" required />
        <label for="expiryDate">EXPIRATION DATE (MM / YY)</label>
        <input type="text" name="expiryDate" placeholder="MM / YY" required />
        <label for="cvv">CV CODE (CVC)</label>
        <input type="text" name="cvv" placeholder="CVC" required />
        <label for="cardOwner">CARD OWNER</label>
        <input type="text" name="cardOwner" placeholder="Card Owner Name" required />
        <input type="submit" value="Confirm Payment" />
    </form>
    <div class="card-images">
        <img src="https://img.icons8.com/color/96/000000/mastercard-logo.png" alt="MasterCard" />
        <img src="https://img.icons8.com/color/96/000000/discover.png" alt="Discover" />
        <img src="https://img.icons8.com/color/96/000000/paypal.png" alt="PayPal" />
        <img src="https://img.icons8.com/color/96/000000/amex.png" alt="American Express" />
    </div>
</div>
<%-- Error message popup (if any) --%>
<% String errorMsg = null;
   if (request.getAttribute("errorMsg") != null) {
       errorMsg = (String) request.getAttribute("errorMsg");
   }
   if (errorMsg != null) { %>
    <div class="error-popup" id="errorPopup">
        <span><%= errorMsg %></span>
        <button class="close-btn" onclick="closeErrorPopup()">&times;</button>
    </div>
<% } %>

<% String successMsg = null;
   if (request.getAttribute("successMsg") != null) {
       successMsg = (String) request.getAttribute("successMsg");
   }
   if (successMsg != null) { %>
    <div class="tab" style="margin: 32px auto; max-width: 700px;">
        <%= successMsg %>
    </div>
<% } %>
</body>
</html>
