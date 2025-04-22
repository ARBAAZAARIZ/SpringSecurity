import { createContext, useContext, useState } from "react";

// This is the context that will be used to provide the theme to all components
const ThemeContext=createContext();

// This is the provider component that will wrap the entire app and provide the theme context to all components
export function ThemeProvider({children}){


const [theme,setTheme]=useState('light');

const toggleTheme=()=>{
    setTheme(prevTheme => prevTheme === 'light'? 'dark':'light');
}

return (
    <ThemeContext.Provider value={{theme,toggleTheme}}>
        {children}
    </ThemeContext.Provider>
)
}

export function useTheme(){
    return useContext(ThemeContext)
}