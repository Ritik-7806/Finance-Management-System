package com.backend.fms.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public ResponseEntity<String> healthCheck(HttpServletRequest request) {

        // Get current base URL (http://localhost:xxxx)
        String baseUrl = ServletUriComponentsBuilder
                .fromRequestUri(request)
                .replacePath(null)
                .build()
                .toString();

        String msg = """
        <h2>Finance Management System Backend</h2>

        <p>This backend provides secure finance record management with 
        JWT authentication, role-based access, MySQL database, Docker support, 
        and Kubernetes deployment.</p>

        <p>
            <b>All APIs and documentation are available on Swagger UI.</b>
        </p>

        <p>
            <a href="%s/swagger-ui/index.html">
                 Open Swagger UI
            </a>
        </p>

        <h3>Overview:</h3>
        <p>
        This project demonstrates real-world backend engineering skills.  
        Users can securely manage income and expense records.  
        Supports roles: <b>Viewer</b>, <b>Analyst</b>, and <b>Admin</b>.  
        Built using a layered architecture with Controller, Service, Repository, Entity, and DTO layers.
        </p>

        <h3>Features:</h3>
        <ul>
            <li>User Signup & Login</li>
            <li>JWT-based Authentication</li>
            <li>BCrypt Password Encryption</li>
            <li>Role-Based Authorization</li>
            <li>Create, View, Update & Delete Finance Records</li>
            <li>Admin APIs for managing privileged users</li>
            <li>Analyst APIs for insights & dashboards</li>
            <li>MySQL Database Integration</li>
            <li>Dockerized Deployment</li>
            <li>Kubernetes Manifests for Deployment & Ingress</li>
        </ul>

        <h3>Tech Stack:</h3>
        <ul>
            <li>Java</li>
            <li>Spring Boot</li>
            <li>Spring Security</li>
            <li>Spring Data JPA</li>
            <li>MySQL</li>
            <li>JWT</li>
            <li>Maven</li>
            <li>Docker</li>
            <li>Kubernetes</li>
        </ul>

        <h3>Architecture:</h3>
        <ul>
            <li>Controller – API Endpoints</li>
            <li>Service – Business Logic</li>
            <li>Repository – Database Operations</li>
            <li>Entity – Persistence Models</li>
            <li>DTO – Request Objects</li>
        </ul>

        <h3>Roles:</h3>
        <ul>
            <li><b>Viewer:</b> Manage own account & records</li>
            <li><b>Analyst:</b> View insights & dashboard data</li>
            <li><b>Admin:</b> Create admins, create analysts, list users, delete users</li>
        </ul>

        <h3>Deployment Options:</h3>
        <ul>
            <li>Local Run</li>
            <li>Docker Compose</li>
            <li>Kubernetes (backend, MySQL, service, PVC, namespace, ingress)</li>
        </ul>

        """.formatted(baseUrl);

        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(msg);
    }

}
