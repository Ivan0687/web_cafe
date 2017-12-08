package name.ivan.boiko.apps;

import name.ivan.boiko.configurations.DatabaseConfiguration;
import name.ivan.boiko.configurations.MvcConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] {DatabaseConfiguration.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] {MvcConfiguration.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }
}
