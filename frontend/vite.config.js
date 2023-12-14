import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
    },
  },
  resolve: {
    alias: {
      src: '/src',
      assets: '/src/assets',
      config: '/src/config',
      components: '/src/components',
      context: '/src/context',
      pages: '/src/pages',
      hooks: '/src/hooks',
      utilities: '/src/utilities',
    },
  },
});
