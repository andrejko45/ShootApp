<script setup>
import { ref, onMounted } from 'vue'
import {
  getUsers,
  createUser,
  deleteUser,
  updateUser
} from '@/services/userService'

const users = ref([])
const showForm = ref(false)
const editMode = ref(false)
const visibleIds = ref({})

const newUser = ref({
  name: '',
  surname: '',
  email: '',
  phoneNumber: '',
})

onMounted(async () => {
  try {
    users.value = await getUsers()
  } catch (error) {
    console.error(error)
  }
})

async function handleSaveUser() {
  try {

    if(editMode.value) {
      await updateUser(newUser.value)
    } else {
      await createUser(newUser.value)
    }

    users.value = await getUsers()

    newUser.value = {
      name: '',
      surname: '',
      email: '',
      phoneNumber: '',
    }

    editMode.value = false
    showForm.value = false

  } catch (error) {
    if(error.response?.data?.message) {
      alert(error.response.data.message)
    }
  }
}

async function handleDeleteUser(id) {
  try {
    await deleteUser(id)
    users.value = await getUsers()
  } catch (error) {
      console.error(error)

      if (error.response?.data?.message) {
        alert(error.response.data.message)
      } else {
        alert('Unexpected error')
      }
  }
}
  function handleEditUser(user) {
    editMode.value = true

    newUser.value = {
      id: user.id,
      name: user.name,
      surname: user.surname,
      email: user.email,
      phoneNumber: user.phoneNumber,
    }

    showForm.value = true
  }

  function toggleId(userId) {
    visibleIds.value[userId] = !visibleIds.value[userId]
  }

</script>

<template>
  <div class="page">
    <div class="header">
      <h1>Používatelia</h1>

      <button class="create-btn" @click="showForm = !showForm">
        + Pridať používateľa
      </button>
    </div>

    <div v-if="showForm" class="form-container">
      <input v-model="newUser.name" placeholder="Meno" />
      <input v-model="newUser.surname" placeholder="Priezvisko" />
      <input v-model="newUser.email" placeholder="Email" />
      <input v-model="newUser.phoneNumber" placeholder="Telefónne číslo" />

      <button class="save-btn" @click="handleSaveUser">
        {{ editMode ? 'Aktualizovať' : 'Uložiť' }}
      </button>

    </div>

    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>Meno</th>
            <th>Priezvisko</th>
            <th>Emailová adresa</th>
            <th>Telefónne číslo</th>
            <th>Akcie</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="user in users" :key="user.id">
            <td>{{ user.name }}</td>
            <td>{{ user.surname }}</td>
            <td>{{ user.email }}</td>
            <td>{{ user.phoneNumber }}</td>

            <td>
              <button
                class="edit-btn"
                @click="handleEditUser(user)"
              >
                Upraviť
              </button>

              <button
                class="delete-btn"
                @click="handleDeleteUser(user.id)"
              >
                Vymazať
              </button>

              <button class="show-id-btn" @click="toggleId(user.id)">
                Ukáž ID
              </button>

                <span
                  v-if="visibleIds[user.id]"
                  class="user-id"
                >
                  ID: {{ user.id }}
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

.user-id {
  margin-left: 10px;
  margin-right: 10px;
  font-weight: bold;
}

</style>
