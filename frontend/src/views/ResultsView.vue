<script setup>
import { ref, onMounted } from 'vue'

import {
  getResults,
  createResult,
  deleteResult
} from '@/services/resultService'

const results = ref([])

const showForm = ref(false)

const visibleIds = ref({})

const newResult = ref({
  partId: '',
  roundId: '',
  points: '',
})

onMounted(async () => {
  try {
    results.value = await getResults()
  } catch (error) {
    console.error(error)
  }
})

async function handleSaveResult() {
  try {
    await createResult(newResult.value)

    results.value = await getResults()

    newResult.value = {
      partId: '',
      roundId: '',
      points: '',
    }

    showForm.value = false

  } catch (error) {

    if (error.response?.data?.message) {
      alert(error.response.data.message)
    } else {
      alert('Unexpected error')
    }

  }
}

async function handleDeleteResult(id) {
  try {
    await deleteResult(id)

    results.value = await getResults()

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
      <h1>Výsledky</h1>

      <button
        class="create-btn"
        @click="showForm = !showForm"
      >
        + Pridať výsledok
      </button>
    </div>

    <div
      v-if="showForm"
      class="form-container"
    >
      <input
        v-model="newResult.partId"
        placeholder="ID Účasti"
      />

      <input
        v-model="newResult.roundId"
        placeholder="ID Kola"
      />

      <input
        v-model="newResult.points"
        placeholder="Body"
      />

      <button
        class="save-btn"
        @click="handleSaveResult"
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
            <th>Kolo</th>
            <th>Body</th>
            <th>Akcie</th>
          </tr>
        </thead>

        <tbody>
          <tr
            v-for="result in results"
            :key="result.id"
          >
            <td>{{ result.userName }}</td>
            <td>{{ result.compName }}</td>
            <td>{{ result.roundName }}</td>
            <td>{{ result.points }}</td>

            <td>
              <button
                class="show-id-btn"
                @click="toggleId(result.id)"
              >
                {{ visibleIds[result.id] ? 'Skry ID' : 'Ukáž ID' }}
              </button>

              <span
                v-if="visibleIds[result.id]"
                class="entity-id"
              >
                ID: {{ result.id }}
              </span>

              <button
                class="delete-btn"
                @click="handleDeleteResult(result.id)"
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
  box-shadow: 0 2px 10px rgba(0,0,0,0.08);
}

table {
  width: 100%;
  border-collapse: collapse;
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
</style>
