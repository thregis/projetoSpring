import React from 'react'
import { ThemeProvider, createMuiTheme } from '@material-ui/core/styles'
import { blue, green } from '@material-ui/core/colors'


const theme = createMuiTheme({
    palette: {
        primary: {
            main: blue[500],
        },
        secondary: {
            main: green[500],
        }
    },
    typography: {
        button: {
            fontSize: '1rem',
        },
    },
})

const Theme = (props) => {
    const { children } = props
    return <ThemeProvider theme={theme}>{children}</ThemeProvider>
}

export const withTheme = (Component) => {
    return (props) => {
        return (
            <Theme>
                <Component {...props} />
            </Theme>
        )
    }
}