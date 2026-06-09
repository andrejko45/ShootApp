<template>
  <aside class="sidebar">
    <h2 class="logo">ShootApp</h2>

    <nav>
      <RouterLink to="/users">Používatelia</RouterLink>
      <RouterLink to="/competitions">Súťaže</RouterLink>
      <RouterLink to="/roles">Role</RouterLink>
      <RouterLink to="/participations">Účasti</RouterLink>
      <RouterLink to="/rounds">Kolá</RouterLink>
      <RouterLink to="/results">Výsledky</RouterLink>
    </nav>
  </aside>
</template>

<style scoped>
.sidebar {
  width: 240px;
  height: 100vh;
  background: #1f2937;
  color: white;
  padding: 20px;
}

.logo {
  margin-bottom: 30px;
}

nav {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

a {
  color: white;
  text-decoration: none;
  padding: 10px;
  border-radius: 6px;
}

a:hover {
  background: #374151;
}

.router-link-active {
  background: #2563eb;
}
</style>
