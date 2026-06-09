<script setup>
import { ref, onMounted } from 'vue'

import {
  getRounds,
  createRound,
  deleteRound
} from '@/services/roundService'

const rounds = ref([])

const showForm = ref(false)

const visibleIds = ref({})

const newRound = ref({
  compId: '',
  name: '',
  orderIndex: '',
  description: '',
})

onMounted(async () => {
  try {
    rounds.value = await getRounds()

    rounds.value.sort(
      (a, b) => a.orderIndex - b.orderIndex
    )

  } catch (error) {
    console.error(error)
  }
})

async function handleSaveRound() {
  try {

    await createRound(newRound.value)

    rounds.value = await getRounds()

    rounds.value.sort(
      (a, b) => a.orderIndex - b.orderIndex
    )

    newRound.value = {
      compId: '',
      name: '',
      orderIndex: '',
      description: '',
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

async function handleDeleteRound(id) {
  try {

    await deleteRound(id)

    rounds.value = await getRounds()

    rounds.value.sort(
      (a, b) => a.orderIndex - b.orderIndex
    )

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
      <h1>Kolá</h1>

      <button
        class="create-btn"
        @click="showForm = !showForm"
      >
        + Pridať kolo
      </button>
    </div>

    <div
      v-if="showForm"
      class="form-container"
    >
      <input
        v-model="newRound.compId"
        placeholder="ID Súťaže"
      />

      <input
        v-model="newRound.name"
        placeholder="Názov kola"
      />

      <input
        v-model="newRound.orderIndex"
        placeholder="Poradie kola"
      />

      <input
        v-model="newRound.description"
        placeholder="Popis"
      />

      <button
        class="save-btn"
        @click="handleSaveRound"
      >
        Uložiť
      </button>
    </div>

    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>Súťaž</th>
            <th>Kolo</th>
            <th>Popis</th>
            <th>Akcie</th>
          </tr>
        </thead>

        <tbody>
          <tr
            v-for="round in rounds"
            :key="round.id"
          >
            <td>{{ round.compName }}</td>
            <td>{{ round.name }}</td>
            <td>{{ round.description }}</td>

            <td>
              <button
                class="show-id-btn"
                @click="toggleId(round.id)"
              >
                {{ visibleIds[round.id] ? 'Skry ID' : 'Ukáž ID' }}
              </button>

              <span
                v-if="visibleIds[round.id]"
                class="entity-id"
              >
                ID: {{ round.id }}
              </span>

              <button
                class="delete-btn"
                @click="handleDeleteRound(round.id)"
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
