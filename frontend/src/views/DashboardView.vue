<template>
  <h1>Dashboard</h1>

  <div class="cards">
    <div class="card">Používatelia</div>
    <div class="card">Súťaže</div>
    <div class="card">Účasti</div>
    <div class="card">Výsledky</div>
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
