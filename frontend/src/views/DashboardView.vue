<template>
  <h1>Dashboard</h1>

  <div class="cards">
    <div class="card">Users</div>
    <div class="card">Competitions</div>
    <div class="card">Participations</div>
    <div class="card">Results</div>
  </div>
</template>

<style scoped>
.cards {
  display: grid;
  grid-template-columns: repeat(2, 250px);
  gap: 20px;
  margin-top: 20px;
}

.card {
  background: white;
  padding: 25px;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}
</style>
