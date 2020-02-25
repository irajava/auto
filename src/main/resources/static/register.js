function f() {
    const API_URL = 'http://localhost:8080';
    const userNameInput=document.getElementById('username_input');
    const passwordInput=document.getElementById('password_input');
    document.getElementById('register_btn').addEventListener('click', ()=>{
        const method =  'POST';
        let xhr = new XMLHttpRequest();
        xhr.open(method, `${API_URL}/user/register`);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                const res = JSON.parse(xhr.response);
                window.localStorage.setItem('autoToken', res.token);
                window.localStorage.setItem('username', res.username);
                window.location.href = '/indexUser';

            }
        };
        xhr.send(JSON.stringify({
            username: userNameInput.value,
            password: passwordInput.value,

        }));
    });


}