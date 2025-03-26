package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@SuppressWarnings("hiding")
@WebFilter("/TableauDeBord2")
public class SessionFilter<FilterConfig> implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter1(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getSession().getAttribute("poids") == null || req.getSession().getAttribute("taille") == null) {
            res.sendRedirect("/login");
        } else {
            chain.doFilter(request, response); 
        }
    }

    public void destroy() {}
	@Override
	public void doFilter(jakarta.servlet.ServletRequest arg0, jakarta.servlet.ServletResponse arg1,
			jakarta.servlet.FilterChain arg2) throws IOException, jakarta.servlet.ServletException {
		
	}
}
