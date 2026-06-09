<script setup>
import { ref, onMounted } from 'vue'

import {
  getParticipations,
  createParticipation,
  deleteParticipation
} from '@/services/participationService'

const participations = ref([])

const showForm = ref(false)

const visibleIds = ref({})

const errorMessage = ref('')
const successMessage = ref('')

const newParticipation = ref({
  userId: '',
  compId: '',
  roleId: '',
})

onMounted(async () => {
  try {
    participations.value = await getParticipations()
  } catch (error) {
    console.error(error)
  }
})

async function handleSaveParticipation() {
  try {
    await createParticipation(newParticipation.value)

    participations.value = await getParticipations()

    newParticipation.value = {
      userId: '',
      compId: '',
      roleId: '',
    }

    showForm.value = false

  }  catch (error) {

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

async function handleDeleteParticipation(id) {
  try {

    if (!confirm('Ste si istý že chcete vymazať túto účasť ?')) {
      return
    }

    await deleteParticipation(id)

    participations.value = await getParticipations()

  } catch (error) {
    if (error.response?.data?.message) {
      alert(error.response.data.message)
    } else {
      alert('Unexpected error')
    }
  }
}

function toggleId(id) {
  visibleIds.value[id] = !visibleIds.value[id]
}
</script>

<template>
  <div class="page">
    <div class="header">
      <h1>Registrácie</h1>

      <button
        class="create-btn"
        @click="showForm = !showForm"
      >
        + Pridať účasť
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

    <div
      v-if="showForm"
      class="form-container"
    >
      <input
        v-model="newParticipation.userId"
        placeholder="ID Používateľa"
      />

      <input
        v-model="newParticipation.compId"
        placeholder="ID Súťaže"
      />

      <input
        v-model="newParticipation.roleId"
        placeholder="ID Role"
      />

      <button
        class="save-btn"
        @click="handleSaveParticipation"
      >
        Uložiť
      </button>
    </div>

    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>Používateľ</th>
            <th>Súťaž</th>
            <th>Rola</th>
            <th>Akcie</th>
          </tr>
        </thead>

        <tbody>
          <tr
            v-for="participation in participations"
            :key="participation.id"
          >
            <td>{{ participation.userName }}</td>
            <td>{{ participation.compName }}</td>
            <td>{{ participation.roleName }}</td>

            <td>
              <button
                class="show-id-btn"
                @click="toggleId(participation.id)"
              >
                {{ visibleIds[participation.id] ? 'Skry ID' : 'Ukáž ID' }}
              </button>

              <span
                v-if="visibleIds[participation.id]"
                class="entity-id"
              >
                ID: {{ participation.id }}
              </span>

              <button
                class="delete-btn"
                @click="handleDeleteParticipation(participation.id)"
              >
                Vymazať
              </button>
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

.table-container {
  background: white;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
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

.show-id-btn {
  background: #2563eb;
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
  border-radius: 5px;
  cursor: pointer;
}

.entity-id {
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
