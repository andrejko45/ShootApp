import axios from 'axios'

const API_URL = 'http://localhost:8080/participations'

export async function getParticipations() {
  const response = await axios.get(API_URL)
  return response.data
}

export async function createParticipation(participation) {
  const response = await axios.post(API_URL, participation)
  return response.data
}

export async function deleteParticipation(id) {
  const response = await axios.delete(`${API_URL}/${id}`)
  return response.data
}
