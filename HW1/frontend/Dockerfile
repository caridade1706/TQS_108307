# Use the latest LTS version of Node.js as the base image
FROM node:20 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the frontend application (assuming you have a build script)
RUN npm run build

# Create a new stage for the final image
FROM nginx:alpine

# Copy the built frontend files from the builder stage to the nginx server
COPY --from=builder /app/dist /usr/share/nginx/html

# Expose port 80, which is the default port for HTTP traffic
EXPOSE 5173

# Start nginx server when the container starts
CMD ["nginx", "-g", "daemon off;"]
