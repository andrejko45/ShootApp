import axios from 'axios'

const API_URL = 'http://localhost:8080/roles'

export async function getRoles() {
  const response = await axios.get(API_URL)
  return response.data
}

export async function createRole(role) {
  const response = await axios.post(API_URL, role)
  return response.data
}

export async function updateRole(role) {
  const response = await axios.put(API_URL, role)
  return response.data
}

export async function deleteRole(id) {
  const response = await axios.delete(`${API_URL}/${id}`)
  return response.data
}
