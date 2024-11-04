# Incident Microservice - MaxiClone

This microservice manages incident reports for the MaxiClone application. Incidents represent issues related to shipments made to stores.

## Endpoints

### 1. **Create Incident**
- **Endpoint:** `POST /api/v1/incidents`
- **Description:** Creates a new incident and allows adding multiple affected products as incident details.
- **Request Body:**
  ```json
  {
    "shipmentId": 1,
    "storeId": 1,
    "userEmail": "example@domain.com",
    "status": "OPEN",
    "solution": "",
    "details": [
      {
        "productSku": "123456",
        "affectedQuantity": 1,
        "reason": "Product is damaged"
      },
      {
        "productSku": "654321",
        "affectedQuantity": 2,
        "reason": "Product is expired"
      }
    ]
  }
  ```
- **Response:** Successfully created incident.

### 2. **Update Incident**
- **Endpoint:** `PUT /api/v1/incidents/{id}`
- **Description:** Updates the status and solution of an existing incident.
- **Path Param:** `id` - ID of the incident to update.
- **Request Body:**
  ```json
  {
    "status": "CLOSED",
    "solution": "Product replaced"
  }
  ```
- **Response:** Successfully updated incident.

### 3. **Get All Incidents by Status**
- **Endpoint:** `GET /api/v1/incidents/status/{status}`
- **Description:** Retrieves all incidents with a specific status, paginated.
- **Query Params:**
    - `status` - The status of the incident (`OPEN` or `CLOSED`).
    - `page` - Page number for pagination.
    - `size` - Page size.
- **Response:** Paginated list of incidents filtered by status.

### 4. **Get Incident by ID**
- **Endpoint:** `GET /api/v1/incidents/{id}`
- **Description:** Retrieves a specific incident by its ID.
- **Path Param:** `id` - ID of the incident.
- **Response:** Detailed information of the incident.

### 5. **Delete Incident**
- **Endpoint:** `DELETE /api/v1/incidents/{id}`
- **Description:** Soft deletes an incident by setting its `deleted_at` field.
- **Path Param:** `id` - ID of the incident to delete.
- **Response:** Successfully deleted incident.

## Project Structure

- **Controller Layer**: Handles HTTP requests and routes them to appropriate use cases.
- **Use Cases**: Implements the business logic for creating, updating, retrieving, and deleting incidents.
- **Output Ports**: Defines interfaces for communication with the persistence layer.
- **Entities**: Defines the domain structures `Incident` and `IncidentDetail`.
- **DTOs**: Contains Data Transfer Objects to facilitate data transfer.

## Prerequisites

- **Java 22**
- **Spring Boot**
- **MySQL** (or another relational database engine)
- **RabbitMQ** (for queuing notifications)
- **OpenAPI/Swagger**: to document and test endpoints.

## Environment Configuration

To set up the environment, adjust the variables in the `application.yml` file or specific profiles (`application-dev.yml`, `application-prod.yml`), including:
- Database configurations
- RabbitMQ configuration for messaging
- Swagger configuration to enable Bearer token in authenticated requests.
