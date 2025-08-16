document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const userId = document.getElementById('username').value;
    localStorage.setItem('userId', userId);
    alert('Logged in! User ID stored.');
});

document.getElementById('logout').addEventListener('click', function() {
    localStorage.removeItem('userId');
    alert('Logged out! User ID removed.');
});