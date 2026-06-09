import axios from 'axios'

const API_URL = 'http://localhost:8080/rounds'

export async function getRounds() {
  const response = await axios.get(API_URL)
  return response.data
}

export async function createRound(round) {
  const response = await axios.post(API_URL, round)
  return response.data
}

export async function deleteRound(id) {
  const response = await axios.delete(`${API_URL}/${id}`)
  return response.data
}
