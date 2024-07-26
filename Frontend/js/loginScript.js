
function validateForm() {
    var fullname = document.getElementById('fullname').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('create-password').value;
    var confirmPassword = document.getElementById('confirm-password').value;

    var emailError = document.getElementById('emailError');
    var passwordError = document.getElementById('passwordError');
    var confirmPasswordError = document.getElementById('confirmPasswordError');
    var validationResult = document.getElementById('validationResult'); // New element for displaying validation result

    // Check if fullname is non-empty
    if (fullname.trim() === '') {
        validationResult.innerText = 'Please enter your full name.';
        return false;
    } else {
        validationResult.innerText = '';
    }

    // Email format validation
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        emailError.innerText = 'Please enter a valid email address.';
        return false;
    } else {
        emailError.innerText = '';
    }

    // Password must contain at least 8 characters, including at least one uppercase letter
    var passwordRegex = /^(?=.*[A-Z]).{8,16}$/;
    if (!passwordRegex.test(password)) {
        passwordError.innerText = 'Password must be 8-16 characters long and include at least one uppercase letter.';
        return false;
    } else {
        passwordError.innerText = '';
    }


    // Confirm password consistency validation
    if (password !== confirmPassword) {
        confirmPasswordError.innerText = 'Passwords mismatch! Please try again.';
        return false;
    } else {
        confirmPasswordError.innerText = '';
    }

    // Validation passed
    return true;
}

document.addEventListener('DOMContentLoaded', function () {
    var form = document.getElementById('loginForm');
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        var formData = new FormData(form);
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://120.26.136.194:8000/api/user/login', true); // 修改此处的URL为登录的URL
        xhr.setRequestHeader('Content-Type', 'application/json');

        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                console.log('Success:', xhr.responseText);
                const jsonResponse = JSON.parse(xhr.responseText);
                if (jsonResponse.success) {
                    const token = jsonResponse.data.token;
                    // 将 token 保存到浏览器的本地存储中
                    localStorage.setItem('token', token);
                    localStorage.setItem('isLoggedIn', true); // 存储登录状态
                    alert('Login successful!')
                    // 跳转回主页面，你需要根据实际情况修改这里的跳转逻辑
                    window.location.href = '../../index.html';
                } else {
                    // success 字段为 false，发出警告
                    alert('Login failed! Check your info again');
                }

            } else {
                console.error('Something went wrong:', xhr.statusText);
            }
        };

        var object = {};
        formData.forEach((value, key) => object[key] = value);
        var json = JSON.stringify(object);

        xhr.send(json);
    });
});


document.addEventListener('DOMContentLoaded', function () {
    var form = document.getElementById('RegisterForm'); // 修改表单ID为注册表单的ID
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        var formData = new FormData(form);
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://http://120.26.136.194:8000/api/user/register', true); // 修改此处的URL为注册的URL
        xhr.setRequestHeader('Content-Type', 'application/json');

        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                console.log('Success:', xhr.responseText);
                const jsonResponse = JSON.parse(xhr.responseText);
                if (jsonResponse.success) {
                    alert('Registration successful!'); // 注册成功
                    // 可以根据实际情况进行跳转或其他操作
                    localStorage.setItem('isLoggedIn', true);
                    window.location.href = '../../index.html';
                } else {
                    // success 字段为 false，发出警告
                    alert('Registration failed! Please check your information again.');
                }

            } else {
                console.error('Something went wrong:', xhr.statusText);
                alert('Registration failed! Please check your information again.');
            }
        };

        var object = {};
        formData.forEach((value, key) => object[key] = value);
        var json = JSON.stringify(object);

        xhr.send(json);
    });
});





