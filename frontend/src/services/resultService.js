import axios from 'axios'

const API_URL = 'http://localhost:8080/results'

export async function getResults() {
  const response = await axios.get(API_URL)
  return response.data
}

export async function createResult(result) {
  const response = await axios.post(API_URL, result)
  return response.data
}

export async function deleteResult(id) {
  const response = await axios.delete(`${API_URL}/${id}`)
  return response.data
}
