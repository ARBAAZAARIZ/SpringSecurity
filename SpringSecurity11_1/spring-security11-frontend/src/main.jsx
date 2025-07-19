
import { createRoot } from 'react-dom/client'

import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Login from './componsnts/Login.jsx'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import Home from './componsnts/Home.jsx'



const routs= createBrowserRouter([

  {
    path: '/',
    element: <App/>,
    children:[
      {
        index: true,
        element: <Login/>
      },
      {
        path: '/login',
        element:<Login/>
      },
      {
        path: '/home',
        element: <Home/>
      }
    ]
  }
]

)
createRoot(document.getElementById('root')).render(
  <RouterProvider router={routs}/>
)
