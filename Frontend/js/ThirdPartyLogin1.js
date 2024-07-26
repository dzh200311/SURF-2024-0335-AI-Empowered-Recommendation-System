const oauthGithub = {
    getCode() {
        const authorize_uri = 'https://github.com/login/oauth/authorize';
        const client_id = '6f1509e4afb4b359bc2a';
        const redirect_uri = 'http://120.26.136.194:8080/subPage/Product/Restaurant.html';
        window.location.href = `${authorize_uri}?scope=user&client_id=${client_id}&redirect_uri=${redirect_uri}`;
    },
    getUser() {
        const code = getQuery('code');
        console.log(code);
        console.log("asasa")
        if (code) {
            $.ajax({
                url: 'http://localhost:8000/oauth/github',
                method: 'POST',
                data: { code: code },
                success: function(response) {
                    if (response.error) {
                        // Emit custom toast for login failure
                        console.log(response.error)
                    } else {
                        // Store user data
                        // Assuming setUser is a function to store user data
                        setUser(response.data);
                        // Emit custom toast for login success
                        console.log(response.data);
                    }
                },
                error: function(xhr, status, error) {
                    // Emit custom toast for login failure
                    console.error(xhr.responseText);
                }
            });
        }
    }
};
//google sign in
const clientId = '559128899303-5oncp9ieu28ktfr3te7fgov2t4o6p9bp.apps.googleusercontent.com';
const script = document.createElement('script');
const handleGoogleSignIn = (response) => {
    // 处理Google登录成功的响应
    console.log('Google登录成功', response);
};
const handleGoogleSignInCancel = () => {
    // 处理Google登录取消
    console.log('Google登录被取消');
};
const initializeGoogleSignIn = (client_id) => {
    window.google.accounts.id.initialize({
        client_id,
        cancel_on_tap_outside: true, // 控制是否在提示之外进行点击时取消提示(关闭一键登录弹窗)，默认true
        auto_select: true, // 开启自动登录功能，默认false
        callback: handleGoogleSignIn, // 验证成功回调
        cancel: handleGoogleSignInCancel,
    });
    // 渲染“使用 Google 帐号登录”按钮
    window.google.accounts.id.renderButton(
        document.getElementById('google-login-button'),
        {
            theme: 'outline',
            size: 'large',
            text: 'login_with',
            shape: 'rectangular',
        },
    );
    // 启用一键登录提示(弹窗)功能
    window.google.accounts.id.prompt();
};

if (!window.google) {
    script.src = 'https://accounts.google.com/gsi/client'; // 加载客户端库
    script.async = true;
    script.onload = () => initializeGoogleSignIn(clientId);
    document.head.appendChild(script);
} else {
    initializeGoogleSignIn(clientId);
}


function setUser(data) {
    console.log(data);
}

function getQuery(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}
