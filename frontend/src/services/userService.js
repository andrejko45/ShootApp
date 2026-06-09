import axios from 'axios'

const API_URL = 'http://localhost:8080/users'

export async function getUsers() {
  const response = await axios.get(API_URL)
  return response.data
}

export async function createUser(user) {
  const response = await axios.post(API_URL, user)
  return response.data
}

export async function deleteUser(id) {
  const response = await axios.delete(`${API_URL}/${id}`)
  return response.data
}

export async function updateUser(user) {
  const response = await axios.put(API_URL, user)
  return response.data
}
