function [dataset] = load_terrain(path)
  file_id = fopen(path);
  data = textscan(file_id,'%f %f %f', 'HeaderLines', 1);
  fclose(file_id);

  dataset = [ data{1} data{2} data{3} ];
end