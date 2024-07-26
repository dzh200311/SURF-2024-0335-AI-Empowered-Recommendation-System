/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./subPage/**/*.{html,js}",
    "./js/**/*.js"
  ],
  theme: {
    extend: {},
  },
  plugins: [

  ],
  darkMode: 'selector',
}
