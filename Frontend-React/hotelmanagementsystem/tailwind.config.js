module.exports = {
    content: [
        "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
        "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    ],
    theme: {
        extend: {
            colors: {
                'hotel-primary': '#1e40af', // Custom blue
                'hotel-secondary': '#ea580c', // Custom orange
            },
        },
    },
    plugins: [],
}