function handler = plot_terrain_2(terrain_data, real_val)
	clf;
	colormap ("summer");
	
	x = terrain_data(:,1);
	y = terrain_data(:,2);
	z = terrain_data(:,3);

	x_linspace=linspace(min(x),max(x),100);
	y_linspace=linspace(min(y),max(y),100);

	[xx yy]=meshgrid(x_linspace,y_linspace);
	zz = griddata(x,y,z,xx,yy);

	handler = surfc(xx,yy,zz);

	hold on;
	scatter3(x, y, real_val(:), 'MarkerFaceColor','r');

endfunction