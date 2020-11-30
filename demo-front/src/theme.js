import { createMuiTheme } from '@material-ui/core/styles'

const theme = createMuiTheme({
    palette: {
        primary: {
          main: '#283593',
          light: '#535da8',
          dark: '#1c2566',
        },
        secondary: {
          main: '#d50000',
          light: '#dd3333',
          dark: '#950000',
        },
    },
    status: {
        danger: "orange",
    },
    typography: {
        h1: {
            fontWeight: "bold",
            fontSize: "50px",
        },
    },
  })

/*const Theme = (props) => {
    const { children } = props
    return <ThemeProvider theme={theme}>{children}</ThemeProvider>
}

export const WithTheme = (Component) => {
    return (props) => {
        return (
            <Theme>
                <Component {...props} />
            </Theme>
        )
    }*/

export default theme