$(function () {
	let jsxc = new JSXC({
		loadConnectionOptions: function (username, password) {
			return Promise.resolve({
				xmpp: {
					url: 'http://172.17.0.2:7070/http-bind',
					domain: 'localhost',
				}
			});
		}
	});

	let formElement = $('#form');
	let usernameElement = $('#username');
	let passwordElement = $('#password');

	jsxc.watchForm(formElement, usernameElement, passwordElement);
});

function submitForm() {
	$("#form").submit()
}
