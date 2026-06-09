<script setup>
import { ref, onMounted } from 'vue'
import {
  getRoles,
  createRole,
  deleteRole,
  updateRole
} from '@/services/roleService'

const roles = ref([])
const showForm = ref(false)
const editMode = ref(false)
const visibleIds = ref({})

const newRole = ref({
  name: '',
  description: '',
})

onMounted(async () => {
  try {
    roles.value = await getRoles()
  } catch (error) {
    console.error(error)
  }
})

async function handleSaveRole() {
  try {

    if(editMode.value) {
      await updateRole(newRole.value)
    } else {
      await createRole(newRole.value)
    }

    roles.value = await getRoles()

    newRole.value = {
      name: '',
      description: '',
    }

    editMode.value = false
    showForm.value = false

  } catch (error) {
    if(error.response?.data?.message) {
      alert(error.response.data.message)
    }
  }
}

async function handleDeleteRole(id) {
  try {
    await deleteRole(id)
    roles.value = await getRoles()
  } catch (error) {
      console.error(error)

      if (error.response?.data?.message) {
        alert(error.response.data.message)
      } else {
        alert('Unexpected error')
      }
  }
}
  function handleEditRole(role) {
    editMode.value = true

    newRole.value = {
      id: role.id,
      name: role.name,
      description: role.description,
    }

    showForm.value = true
  }

  function toggleId(roleId) {
    visibleIds.value[roleId] = !visibleIds.value[roleId]
  }

</script>

<template>
  <div class="page">
    <div class="header">
      <h1>Role</h1>

      <button class="create-btn" @click="showForm = !showForm">
        + Pridať rolu
      </button>
    </div>

    <div v-if="showForm" class="form-container">
      <input v-model="newRole.name" placeholder="Názov" />
      <input v-model="newRole.description" placeholder="Popis" />

      <button class="save-btn" @click="handleSaveRole">
        {{ editMode ? 'Aktualizovať' : 'Uložiť' }}
      </button>

    </div>

    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>Názov</th>
            <th>Popis</th>
            <th>Akcie</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="role in roles" :key="role.id">
            <td>{{ role.name }}</td>
            <td>{{ role.description }}</td>

            <td>
              <button
                class="edit-btn"
                @click="handleEditRole(role)"
              >
                Upraviť
              </button>

              <button
                class="delete-btn"
                @click="handleDeleteRole(role.id)"
              >
                Vymazať
              </button>

              <button class="show-id-btn" @click="toggleId(role.id)">
                Ukáž ID
              </button>

                <span
                  v-if="visibleIds[role.id]"
                  class="role-id"
                >
                  ID: {{ role.id }}
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

.role-id {
  margin-left: 10px;
  margin-right: 10px;
  font-weight: bold;
}

</style>
