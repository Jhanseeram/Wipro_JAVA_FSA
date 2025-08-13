document.addEventListener('DOMContentLoaded', function() {
  const form = document.getElementById('loginForm');
  const email = document.getElementById('email');
  const password = document.getElementById('password');
  const emailError = document.getElementById('emailError');
  const passwordError = document.getElementById('passwordError');

  function validateEmail() {
    const value = email.value.trim();
    if (!value) {
      email.classList.add('is-invalid');
      emailError.style.display = 'block';
      emailError.textContent = 'Email is required.';
      return false;
    }
    if (!/^\S+@\S+\.\S+$/.test(value)) {
      email.classList.add('is-invalid');
      emailError.style.display = 'block';
      emailError.textContent = 'Invalid email format.';
      return false;
    }
    email.classList.remove('is-invalid');
    emailError.style.display = 'none';
    emailError.textContent = '';
    return true;
  }

  function validatePassword() {
    if (!password.value.trim()) {
      password.classList.add('is-invalid');
      passwordError.style.display = 'block';
      passwordError.textContent = 'Password is required.';
      return false;
    }
    password.classList.remove('is-invalid');
    passwordError.style.display = 'none';
    passwordError.textContent = '';
    return true;
  }

  email.addEventListener('blur', validateEmail);
  password.addEventListener('blur', validatePassword);

  form.addEventListener('submit', function(e) {
    e.preventDefault();
    const validEmail = validateEmail();
    const validPassword = validatePassword();
    if (validEmail && validPassword) {
      alert('Login Successful!');
    }
  });
});