package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeographicPointTO {
	
	private double latitude;
	private double longitude;

	
	@Override
	public String toString() {
	    return "GeographicPoint{ Latitude : " + latitude+ " | Longitude : " + longitude + "}";

	}
}

	
