<script setup>
import { ref, onMounted } from 'vue'

import { getUsers } from '@/services/userService'
import { getCompetitions } from '@/services/competitionService'
import { getParticipations } from '@/services/participationService'
import { getResults } from '@/services/resultService'

const userCount = ref(0)
const competitionCount = ref(0)
const participationCount = ref(0)
const resultCount = ref(0)
const roleCount = ref(0)
const roundCount = ref(0)

onMounted(async () => {
  userCount.value = (await getUsers()).length
  competitionCount.value = (await getCompetitions()).length
  participationCount.value = (await getParticipations()).length
  resultCount.value = (await getResults()).length
  roleCount.value = (await getRoles()).length
  roundCount.value = (await getRounds()).length
})
</script>



<template>
  <h1>Dashboard</h1>

 <div class="cards">
   <div class="card">
     <h2>👤 Používatelia</h2>
     <h1>{{ userCount }}</h1>
   </div>

   <div class="card">
     <h2>🏆 Súťaže</h2>
     <h1>{{ competitionCount }}</h1>
   </div>

   <div class="card">
     <h2>🎖️ Role</h2>
     <h1>{{ roleCount }}</h1>
   </div>

   <div class="card">
     <h2>📝 Registrácie</h2>
     <h1>{{ participationCount }}</h1>
   </div>

   <div class="card">
     <h2>🎯 Kolá</h2>
     <h1>{{ roundCount }}</h1>
   </div>

   <div class="card">
     <h2>📊 Výsledky</h2>
     <h1>{{ resultCount }}</h1>
   </div>
 </div>
</template>

<style scoped>
.cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-top: 30px;
}

.card {
  background: white;
  padding: 25px;
  border-radius: 12px;
  text-align: center;
  box-shadow: 0 2px 10px rgba(0,0,0,0.08);
}

.card h1 {
  font-size: 42px;
  margin-top: 15px;
  color: #2563eb;
}

</style>
