import axios from "axios"

const api = axios.create({
    baseURL: "http://localhost:3000/",
	headers: {
		'Content-type': 'application/json; charset=UTF-8',
		accept: 'application/json,',
	}
})

export const apis = {
    get: () => api.get('/post')
}