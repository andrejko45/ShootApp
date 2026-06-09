<script setup>
import { ref, onMounted } from 'vue'
import {
  getCompetitions,
  createCompetition,
  deleteCompetition,
  updateCompetition
} from '@/services/competitionService'

const competitions = ref([])
const showForm = ref(false)
const editMode = ref(false)
const visibleIds = ref({})
const errorMessage = ref('')
const successMessage = ref('')

const newCompetition = ref({
  name: '',
  place: '',
  date: '',
})

onMounted(async () => {
  try {
    competitions.value = await getCompetitions()
  } catch (error) {
    console.error(error)
  }
})

async function handleSaveCompetition() {
  try {

    if(editMode.value) {
      await updateCompetition(newCompetition.value)
    } else {
      await createCompetition(newCompetition.value)
      successMessage.value = 'Súťaž bola vytvorená úspešne !'
    }

    competitions.value = await getCompetitions()

    newCompetition.value = {
      name: '',
      place: '',
      date: '',
    }

    editMode.value = false
    showForm.value = false

  } catch (error) {

      if (error.response?.data?.errors) {
        errorMessage.value = error.response.data.errors.join(', ')
        return
      }

      if (error.response?.data?.message) {
        errorMessage.value = error.response.data.message
        return
      }

      errorMessage.value = 'Unexpected error'
    }
}

async function handleDeleteCompetition(id) {
  try {

    if (!confirm('Ste si istý že chcete vymazať túto súťaž ?')) {
      return
    }

    await deleteCompetition(id)

    competitions.value = await getCompetitions()
  } catch (error) {
      console.error(error)

      if (error.response?.data?.message) {
        alert(error.response.data.message)
      } else {
        alert('Unexpected error')
      }
  }
}
  function handleEditCompetition(competition) {
    editMode.value = true

    newCompetition.value = {
      id: competition.id,
      name: competition.name,
      place: competition.place,
      date: competition.date,
    }

    showForm.value = true
  }

  function toggleId(competitionId) {
    visibleIds.value[competitionId] = !visibleIds.value[competitionId]
  }

</script>

<template>
  <div class="page">
    <div class="header">
      <h1>Súťaže</h1>

      <button class="create-btn" @click="showForm = !showForm">
        + Pridať súťaž
      </button>
    </div>

    <div
      v-if="errorMessage"
      class="error-box"
    >
      {{ errorMessage }}
    </div>

     <div
          v-if="successMessage"
          class="success-box"
        >
          {{ successMessage }}
     </div>

    <div v-if="showForm" class="form-container">
      <input v-model="newCompetition.name" placeholder="Názov" />
      <input v-model="newCompetition.place" placeholder="Miesto" />
      <input v-model="newCompetition.date" placeholder="Dátum(YYYY-MM-DD)" />

      <button class="save-btn" @click="handleSaveCompetition">
        {{ editMode ? 'Aktualizovať' : 'Uložiť' }}
      </button>

    </div>



    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>Názov</th>
            <th>Miesto</th>
            <th>Dátum</th>
            <th>Akcie</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="competition in competitions" :key="competition.id">
            <td>{{ competition.name }}</td>
            <td>{{ competition.place }}</td>
            <td>{{ competition.date }}</td>
            <td>
              <button
                class="edit-btn"
                @click="handleEditCompetition(competition)"
              >
                Upraviť
              </button>

              <button
                class="delete-btn"
                @click="handleDeleteCompetition(competition.id)"
              >
                Vymazať
              </button>

              <button class="show-id-btn" @click="toggleId(competition.id)">
                Ukáž ID
              </button>

                <span
                  v-if="visibleIds[competition.id]"
                  class="competition-id"
                >
                  ID: {{ competition.id }}
                </span>

            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped>
.page {
  padding: 20px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 25px;
}

h1 {
  margin: 0;
}

.create-btn {
  background: #2563eb;
  color: white;
  border: none;
  padding: 10px 16px;
  border-radius: 6px;
  cursor: pointer;
}

.create-btn:hover {
  background: #1d4ed8;
}

.table-container {
  background: white;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0,0,0,0.08);
}

table {
  width: 100%;
  border-collapse: collapse;
}

tbody tr:hover {
  background: #f9fafb;
}


th {
  background: #f3f4f6;
  text-align: left;
  padding: 14px;
}

td {
  padding: 14px;
  border-top: 1px solid #e5e7eb;
}

.edit-btn {
  background: #f59e0b;
  color: white;
  border: none;
  padding: 6px 10px;
  margin-right: 8px;
  border-radius: 5px;
  cursor: pointer;
}

.delete-btn {
  background: #dc2626;
  color: white;
  border: none;
  padding: 6px 10px;
  margin-right: 8px;
  border-radius: 5px;
  cursor: pointer;
}

.form-container {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
  flex-wrap: wrap;
}

.form-container input {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 6px;
}

.save-btn {
  background: green;
  color: white;
  border: none;
  padding: 10px 16px;
  border-radius: 6px;
  cursor: pointer;
}

.show-id-btn {
  background: #2563eb;
  color: white;
  border: none;
  padding: 6px 10px;
  margin-right: 8px;
  border-radius: 5px;
  cursor: pointer;
}

.competition-id {
  margin-left: 10px;
  margin-right: 10px;
  font-weight: bold;
}

.success-box {
  margin-bottom: 20px;
  padding: 12px;
  border-radius: 8px;
  background: #dcfce7;
  color: #166534;
  border: 1px solid #bbf7d0;
}

.error-box {
  margin-bottom: 20px;
  padding: 12px;
  border-radius: 8px;
  background: #fee2e2;
  color: #991b1b;
  border: 1px solid #fecaca;
}

</style>
