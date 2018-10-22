function post(url, data = {}) {
  return fetch(`http://127.0.0.1:3000${url}`, {
    method: 'POST',
    mode: 'cors', // no-cors, cors, *same-origin
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, same-origin, *omit
    body: JSON.stringify(data),
  })
    .then(response => {
      response.json();
    })
    .catch(error => {
      return error;
    });
}

function get(url) {
  return fetch(`http://127.0.0.1:3000${url}`, {
    method: 'GET',
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
  })
    .then(response => {
      response.json();
    })
    .catch(error => {
      return error;
    });
}

const api = {
  post,
  get,
};

export default api;
