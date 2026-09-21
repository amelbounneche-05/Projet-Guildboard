import { StrictMode } from 'react'

// createRoot = creates the root, attaches React to the HTML
import { createRoot } from 'react-dom/client'
import './index.css'

// App gives (exports) its component, here I take (import) it
import App from './App.tsx'
createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
