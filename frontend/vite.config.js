import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: 'localhost:8080',
      },
    },
  },
  resolve: {
    alias: {
      src: '/src',
      assets: '/src/assets',
      config: '/src/config',
      components: '/src/components',
      pages: '/src/pages',
      utilities: '/src/utilities',
    },
  },
});
