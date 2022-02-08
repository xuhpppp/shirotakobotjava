import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Random;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	public String prefix = ",";
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		//punch some1
		if (args[0].equalsIgnoreCase(prefix + "punch")) {
			if (args.length == 1) {
				event.getMessage().getChannel().sendMessage("Đấm ai, nhớ mention vào nhé :3").queue();
			} else if (args.length == 2) {
				Member member = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
				
				String[] punchList = new String[] {"https://i.pinimg.com/originals/95/2b/a6/952ba6ad6341385c742ecceacd57de87.gif",
													"https://i.gifer.com/61i9.gif",
													"https://i.kym-cdn.com/photos/images/newsfeed/001/856/131/1af.gif",
													"https://i.pinimg.com/originals/81/2c/38/812c384875e4b52dbd7c34d6d8480fb7.gif",
													"https://i.makeagif.com/media/10-04-2015/2rtp-R.gif",
													"http://pa1.narvii.com/6246/ab5cccdbaa2f73bdd93ab67bb2bf7a046df1e95e_00.gif",
													"https://cdn.discordapp.com/attachments/903487650047094795/935100227986284554/IMG_1384.gif",
													"https://cdn.discordapp.com/attachments/903487650047094795/935101207389827082/df8af24e5756ecf4a4e8af0c9ea6499b.gif",
													"https://cdn.discordapp.com/attachments/903487650047094795/935101304823484426/HirD.gif",
													"https://media.giphy.com/media/XKO2OnnJnmqxW/giphy.gif",
													"https://cdn.discordapp.com/attachments/903487650047094795/935101819598827520/oNcHdPX.gif",
													"https://cdn.discordapp.com/attachments/903487650047094795/935101959709548614/AcclaimedCheerfulAtlanticblackgoby-size_restricted.gif",
													"https://i.pinimg.com/originals/48/d5/59/48d55975d1c4ec1aa74f4646962bb815.gif",
													"https://cdn.discordapp.com/attachments/903487650047094795/935102024486371358/tumblr_3e8f6e4d052ceeb977ce8f0ac7434fa9_88c73ba3_500.gif",
													"https://i0.wp.com/myotakuworld.com/wp-content/uploads/2020/06/Eren-Punch%E2%80%99s-Titan-1.gif?resize=400%2C225&is-pending-load=1",
													"https://i0.wp.com/myotakuworld.com/wp-content/uploads/2020/06/Vrog-Punch%E2%80%99s-Makunouchi-Ippo-1.gif?resize=400%2C225&is-pending-load=1",
													"https://i0.wp.com/myotakuworld.com/wp-content/uploads/2020/06/Luffy-punch%E2%80%99s-Bellamy-1.gif?resize=450%2C253&is-pending-load=1",
													"https://i0.wp.com/myotakuworld.com/wp-content/uploads/2020/06/Saitama-Punches-Titan-1.gif?resize=350%2C174&is-pending-load=1",
													"https://cdn.discordapp.com/attachments/903487650047094795/935102386714849280/35d.gif",
													"https://i.pinimg.com/originals/a5/c6/67/a5c667cc1ab513c5e9ac5ccf93fbf9d6.gif",
													"https://i.pinimg.com/originals/cc/39/ac/cc39acfbc8b606696e207a07b52fdd08.gif"};
				int random = new Random().nextInt(punchList.length);
				
				//dam' Nha?
				int flag = 0;
				List<Role> roles = member.getRoles();
				for (int i = 0; i < roles.size(); i++) {
					if (roles.get(i).getName().equals("Trùm tuộc")) {
						flag = 1;
					}
				}
				
				if (flag == 0) {
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle(event.getMessage().getMember().getUser().getName() + " đang đấm " + member.getUser().getName());
					embed.setImage(punchList[random]);
					embed.setColor(0xfcde95);
					event.getMessage().getChannel().sendMessage(embed.build()).queue();
					embed.clear();
				} else {
					event.getMessage().getChannel().sendMessage("Tính làm phản hả").queue();
				}
			}
		}
		
		//mute some1
		if (args[0].equalsIgnoreCase(prefix + "mute")) {
			List<Role> roles = event.getMessage().getMember().getRoles();
			
			int flag = 0;
			for (int i = 0; i < roles.size(); i++) {
				if (roles.get(i).getName().equals("Hội bô lão") || roles.get(i).getName().equals("Suýt trùm tuộc")) {
					flag = 1;
				}
			}
			
			if (flag != 1) {
				event.getMessage().getChannel().sendMessage("Quyền gì mà mute người khác").queue();
			} else if (args.length >= 3) {
				Member member = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
				
				Role mute = event.getGuild().getRoleById("915949316093390939");
				
				event.getGuild().addRoleToMember(member, mute).queue();
				
				long time = 0;
				String reason = "Do ";
				for (int i = 0; i < args[2].length()-1; i++) {
					time = time*10 + (args[2].charAt(i) - '0');
				}
				if (Character.compare(args[2].charAt(args[2].length()-1), 'h') == 0) {
					time *= 60;
				}
				if (args.length > 3) {
					for (int i = 3; i < args.length; i++) {
						reason += args[i] + " ";
					}
				}
				reason += "nên " + member.getUser().getName() + " đã bị khóa mõm  " + time + " phút";
				
				if (time == 0) {
					event.getMessage().getChannel().sendMessage("Cú pháp là ,mute + @người bị mute + thời gian + lý do bị mute").queue();
				} else {
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle(reason);
					embed.setColor(0xfcde95);
					event.getMessage().getChannel().sendMessage(embed.build()).queue();
					embed.clear();
					
					new java.util.Timer().schedule(new java.util.TimerTask() {

						@Override
						public void run() {
							event.getGuild().removeRoleFromMember(member, mute).queue();
						}
						
					}, time * 60000);
				}
			} else  {
				event.getMessage().getChannel().sendMessage("Cú pháp là ,mute + @người bị mute + thời gian + lý do bị mute").queue();
			}
		}
		
		//unmute some1
		if (args[0].equalsIgnoreCase(prefix + "unmute")) {
			Member member = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
			
			Role mute = event.getGuild().getRoleById("915949316093390939");
			
			List<Role> roles = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0)).getRoles();
			
			for (int i = 0; i < roles.size(); i++) {
				if (roles.get(i).getName().equals("Muted")) {
					event.getGuild().removeRoleFromMember(member, mute).queue();
					
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle(member.getNickname() + " đã được ân xá");
					embed.setColor(0xfcde95);
					event.getMessage().getChannel().sendMessage(embed.build()).queue();
					embed.clear();
				}
			}
		}
		
		if (args[0].equalsIgnoreCase(prefix + "download")) {	
			//nhentai
			if (args[1].contains("nhentai") || args[1].contains("Nhentai")) {
				
				String code = "";
				for (int i = 0; i < args[1].length(); i++) {
					if (args[1].charAt(i) >= '0' && args[1].charAt(i) <= '9') {
						code += args[1].charAt(i);
					}
				}
				
				event.getMessage().getChannel().sendMessage("Đang xử lí... (vui lòng chờ từ 2-3 phút)").queue();
				
				CreateFolder.createFolder(code);
				
				int countPage = CountPageNhentai.countPageNhentai("https://nhentai.net/g/" + code);	
				String destinationFile = "D:\\jee-2021-09/ShirotakoBot2/" + code + "/";
				try {
					String gallery = GetGallery.getGallery("https://nhentai.net/g/" + code);
					DownloadNhentai.downloadNhentai(countPage, destinationFile, gallery);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				
				ZipFolder.zipFolder(code);
				event.getMessage().getChannel().sendMessage("Đang nén tệp zip...").queue();
				
				try {
					event.getMessage().getChannel().sendMessage("Đang upload lên google drive...").queue();
					String fileId = DriveQuickstart.driveQuickstart(code);
					
					event.getMessage().reply("https://drive.google.com/uc?export=download&id=" + fileId).queue();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
