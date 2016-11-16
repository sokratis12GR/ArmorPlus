class String
  def underscore
    self.gsub(/::/, '/').
    gsub(/([A-Z]+)([A-Z][a-z])/,'\1_\2').
    gsub(/([a-z\d])([A-Z])/,'\1_\2').
    tr("-", "_").
    downcase
  end
end

Dir.glob("*.{json,png}").each do |entry|
  # Renaming the files while adding `_` between the words and making them lowercase
  next if entry == '.' || entry == '..'
  new_entry = entry.underscore
  File.rename(entry, new_entry)
  puts "Renamed #{entry} to #{new_entry}."
end