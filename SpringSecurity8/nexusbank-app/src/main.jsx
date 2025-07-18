
import { createRoot } from 'react-dom/client'
import App from './App'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { ThemeProvider } from './components/context/ThemeContext';
import Register from './components/register/Register';
import Notices from './components/notices/Notices';
import Login from './components/login/LoginPage';
import Contact from './components/contact/Contact';
import LoginPage from './components/login/LoginPage';
import Home from './components/home/Home';
import Profile from './components/profile/Profile';




const routes = createBrowserRouter([
  {
    path:'/',
    element:<App/>,
    children:[
      {
        path:'/home',
        element:<Home/>
      },
      {
        path:'/register',
        element:<Register/>
      },{
        path:'/notices',
        element:<Notices/>
      },{
        path:'/contact',
        element:<Contact/>
      },{
        path:'/login',
        element:<LoginPage/>
      },{
        path:'/profile',
        element:<Profile/>
      }
    ]
  }
])





createRoot(document.getElementById('root')).render(
 <ThemeProvider>
   <RouterProvider router={routes} />
 </ThemeProvider>
)
