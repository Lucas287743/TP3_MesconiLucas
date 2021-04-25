package ar.edu.unju.edm.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.util.Date;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Cliente {
	private String tipoDocumento;
	private int nroDocumento;
	private String nombreApellido;
	private String email;
	private String password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	private int codigoAreaTelefono;
	private int nroTelefono;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaUltimaCompra;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	public int getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(int nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public int getEdad() {
		int edad=0;
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, hoy);
		edad = periodo.getYears();
		return edad;
	}
	
	public String getDatoAdicional() {
		String datos;
		datos = getTiempoDesdeUltCompraHastaHoy() + "||" + getDiasDesdeNacimHastaHoy() + "||" + getTCumple();
		return datos;
	}
	
	public String getTiempoDesdeUltCompraHastaHoy() {
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(fechaUltimaCompra, hoy);
		return "TUCompra " + String.valueOf(periodo.getYears()) + 
							"-" + String.valueOf(periodo.getMonths()) + 
									"-" + String.valueOf(periodo.getDays());		 	
	}
	
	public String getDiasDesdeNacimHastaHoy() {
		LocalDate hoy = LocalDate.now();
		long dias = ChronoUnit.DAYS.between(fechaNacimiento, hoy);
		return "TFNacim Días:" + String.valueOf(dias);
			
	}
	
	public String getTCumple() {
		//con localdate obtengo año mes y dia
		LocalDate hoy = LocalDate.now();
		LocalDate Nacim = fechaNacimiento;
		LocalDate proximocumple = LocalDate.of(hoy.getYear(),Nacim.getMonth(),Nacim.getDayOfMonth());
		if(proximocumple.isBefore(hoy)) {
			proximocumple = LocalDate.of(proximocumple.getYear()+1, proximocumple.getMonth(), proximocumple.getDayOfMonth());
		}
		Period periodo = Period.between(hoy,proximocumple);
		
		//con localdatetime obtengo hora minuto y segundo
		LocalDateTime ahora = LocalDateTime.now();
		//Le agrego hora, minuto y segundo en 00:00:00
		LocalDateTime cumpleReciente = LocalDateTime.of(proximocumple.getYear(),			//año
														proximocumple.getMonthValue(),		//mes
														proximocumple.getDayOfMonth(),		//dia
																			0,		//hora
																			0,		//minuto
																			0);		//segundo
        Duration duracion = Duration.between(ahora, cumpleReciente);
		
		return "TCumple D" + periodo.getDays() + "-M" + periodo.getMonths() + "-Y" +
							periodo.getYears() + " h" + duracion.toHoursPart() + "-m" +
								duracion.toMinutesPart() + "-s" + duracion.toSecondsPart();
	}
}
