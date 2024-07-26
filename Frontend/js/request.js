document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('loginForm').addEventListener('submit', function (e) {
        e.preventDefault();

        var xhr = new XMLHttpRequest();
        var formData = new FormData(this);

        xhr.open('POST', 'http://localhost:8000/submit-your-login-form', true);
        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                console.log('Login successful:', xhr.responseText);
            } else {
                console.error('Login failed:', xhr.statusText);
            }
        };
        xhr.onerror = function () {
            console.error('Request failed');
        };

        xhr.send(formData);
    });
});
