<template>
  <aside class="sidebar">
    <h2 class="logo">ShootApp</h2>

    <nav>
      <RouterLink to="/users">Users</RouterLink>
      <RouterLink to="/competitions">Competitions</RouterLink>
      <RouterLink to="/roles">Roles</RouterLink>
      <RouterLink to="/participations">Participations</RouterLink>
      <RouterLink to="/rounds">Rounds</RouterLink>
      <RouterLink to="/results">Results</RouterLink>
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
