
import { createRoot } from 'react-dom/client'

import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import LoginPage from './component/LoginPage.jsx'
import Profile from './component/Profile.jsx'


const routes = createBrowserRouter([
  {
    path:"/",
    element:<App/>,
    children:[
      {
        path:"/login",
        element:<LoginPage/>
      },{
        path:"/profile",
        element:<Profile/>
      }
    ]
  }
])


createRoot(document.getElementById('root')).render(
  <RouterProvider router={routes}/>
)
