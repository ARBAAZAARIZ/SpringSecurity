import { Outlet } from "react-router-dom"
import Navbar from "./componsnts/Navbar"

function App() {
  

  return (
    <>
    <Navbar/>
    <Outlet/>
    </>
  )
}

export default App
