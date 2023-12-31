import React from 'react';
import ReactDOM from 'react-dom/client';
import { UserProvider } from '@src/context';
import App from './Application.jsx';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <UserProvider>
      <App />
    </UserProvider>
  </React.StrictMode>,
);
