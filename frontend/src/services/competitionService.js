import axios from 'axios'

const API_URL = 'http://localhost:8080/competitions'

export async function getCompetitions() {
  const response = await axios.get(API_URL)
  return response.data
}

export async function createCompetition(competition) {
  const response = await axios.post(API_URL, competition)
  return response.data
}

export async function deleteCompetition(id) {
  const response = await axios.delete(`${API_URL}/${id}`)
  return response.data
}

export async function updateCompetition(competition) {
  const response = await axios.put(API_URL, competition)
  return response.data
}
