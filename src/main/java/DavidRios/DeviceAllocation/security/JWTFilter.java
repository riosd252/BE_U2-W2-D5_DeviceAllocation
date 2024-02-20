package DavidRios.DeviceAllocation.security;

import DavidRios.DeviceAllocation.entities.Employee;
import DavidRios.DeviceAllocation.exceptions.UnauthorizedException;
import DavidRios.DeviceAllocation.services.EmployeeService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private EmployeeService employeeService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        if (requestHeader == null || !requestHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Token is missing!");

        String accessToken = requestHeader.substring(7);

        jwtTools.verifyToken(accessToken);

        String employeeUuid = jwtTools.extractIdFromToken(accessToken);
        Employee employee = employeeService.findById(UUID.fromString(employeeUuid));

        Authentication authentication = new UsernamePasswordAuthenticationToken(employee, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
