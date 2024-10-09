package converter;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import model.entity.Team;
import service.TeamService;

@FacesConverter(value = "teamConverter", managed = true)
public class TeamConverter implements Converter<Object>{
	
	private TeamService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			service = CDI.current().select(TeamService.class).get();
			return service.findById(Long.parseLong(value));
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		try {
			Team team = (Team)value;
			return team.getId().toString();
		}
		catch(Exception e) {
			return null;
		}
	}

}